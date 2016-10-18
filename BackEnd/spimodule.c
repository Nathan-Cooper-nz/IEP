#include <ftdi.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <stdint.h>
#include <time.h>

# define SPI_MAX_MSG_SIZE (3*8)//previously 64*1024 //changing this didnt do anything....
# define DEFAULT_MEM_SIZE ((SPI_MAX_MSG_SIZE)+9)

# define CLOCK_MAX_SPEEDX5 30000000 /**< @brief Clock max speed in Hz for H class device */
# define CLOCK_MAX_SPEED    6000000 /**< @brief Clock max speed in Hz */
# define CLOCK_MIN_SPEED        100 /**< @brief Clock min speed in Hz */

#define TCK_X5 0x8a

# define FTDISPI_GPO0 0x10 /**< @brief General Purpose Output bits 0 (D4) */
# define FTDISPI_GPO1 0x20 /**< @brief General Purpose Output bits 1 (D5) */
# define FTDISPI_GPO2 0x40 /**< @brief General Purpose Output bits 2 (D6) */
# define FTDISPI_GPO3 0x80 /**< @brief General Purpose Output bits 3 (D7) */



#define BIT_P_CS 0x08
#define BIT_P_DI 0x04
#define BIT_P_DO 0x02
#define BIT_P_SK 0x01
#define BIT_P_G0 FTDISPI_GPO0
#define BIT_P_G1 FTDISPI_GPO1
#define BIT_P_G2 FTDISPI_GPO2
#define BIT_P_G3 FTDISPI_GPO3
#define BIT_P_GX (FTDISPI_GPO0|FTDISPI_GPO1|FTDISPI_GPO2|FTDISPI_GPO3)

#define BIT_DIR (BIT_P_SK|BIT_P_DO|BIT_P_CS|BIT_P_G0|BIT_P_G1|BIT_P_G2|BIT_P_G3)

#define BBMODE_NORMAL 1
#define BBMODE_SPI 2
/** @brief returned when no error */
# define FTDISPI_ERROR_NONE 0
/** @brief returned when a function is called with a non/bad init context */
# define FTDISPI_ERROR_CTX -1
/** @brief returned when a command is imposible */
# define FTDISPI_ERROR_CMD -2
/** @brief returned on allocation problems */
# define FTDISPI_ERROR_MEM -3
/** @brief returned on libftdi error */
# define FTDISPI_ERROR_LIB -4
/** @brief returned on clock error */
# define FTDISPI_ERROR_CLK -5
/** @brief returned on timeout error */
# define FTDISPI_ERROR_TO  -6

#define RETRY_MAX       10
#define RETRY_TIME      1000

//JUST CHECKING THING I DONT REALLY KNOW.
#define ASSERT_CHECK(TEST,MSG,RVAL)do{       \
  if((TEST)){                               \
    fprintf(stderr, "ASSERT: %s\n", MSG );  \
    return RVAL;                            \
  }                                         \
}while(0)

//UM SO I PUT A  SEMICOLON HERE  V AND everything works....
#define FTDI_CHECK(FUN, MSG, CTX);do{                  \
        if ((FUN) < 0)                                  \
        {                                               \
            fprintf(stderr,                             \
                    "%s: %s\n",                         \
                    MSG,                                \
                    ftdi_get_error_string(&CTX));       \
            return FTDISPI_ERROR_LIB;                   \
        }                                               \
    } while (0)

struct spi_context{
  struct ftdi_context fc;
  uint8_t wr_cmd;  /**< @brief write command */
  uint8_t rd_cmd;  /**< @brief read command */
  uint8_t bitini;  /**< @brief initial states of all bits */
  uint8_t *mem;    /**< @brief memory region for write and read functions */
  size_t memsize; /**sizze of the memory region for write and read functions*/
};

static int spi_realloc(struct spi_context * fsc, size_t size);
static int spi_wait(struct spi_context * fsc, uint8_t mask, uint8_t value, int maxtry);

//OPEN FOR SPI
int spi_open(struct spi_context *fsc, struct ftdi_context *fc, int interface){
  ASSERT_CHECK(!fc || !fsc, "CONTEXT NOT INITIALIZED",FTDISPI_ERROR_CTX);
   //fills errything with 0s
  memset(fsc,0,sizeof(*fsc));
  /**fills the spi_context's ftdi_context to be
   the same as the ftdi_context created earlier*/
  memcpy(&fsc->fc,fc,sizeof(*fc));
  /**allocate memory for mem. if success then the memory size is the DEFULT_MEM_SIZE*/
  if(0!=(fsc->mem = (uint8_t*)malloc(DEFAULT_MEM_SIZE))){//CASTING(uint8_t)...not sure of effect
    fsc->memsize = DEFAULT_MEM_SIZE;
  }
  /**Setting chunk size..
  Configure write buffer chunk size, Defult is 4096*/
  FTDI_CHECK(ftdi_write_data_set_chunksize(&fsc->fc,512),"SET CHUNK 512",fsc->fc);
  // printf("SET CHUNK 512\n");

  //weird error in interface ATOM has a cry but compiler happy :)
  //opens selected Channel on chip :)
  FTDI_CHECK(ftdi_set_interface(&fsc->fc,interface), "SET INT", fsc->fc);
  // printf("INTERFACE SET: %d\n", interface);

  //RESETS THE DEVICE, OSCILLOSCOPE IN OUR CASE//
  FTDI_CHECK(ftdi_usb_reset(&fsc->fc),"RESET",fsc->fc);
  // printf("DEVICE RESET\n");
  //GOTS ME MUCH TRIGGERED!!!! 2ms!!! thats quite some time.
  //WE WILL KEEP THIS AT 2 for now. it stores USB data in internall buffer if
  //it is not full to decrease load on USB...hopefully no slowing down is occuring :)
  FTDI_CHECK(ftdi_set_latency_timer(&fsc->fc,2),"SET LAT 2ms", fsc->fc);
  // printf("USB LATENCY TIMER: 2ms\n");
  //I ACTUALLY HAVE NO CLUE WHAT THIS DOES...HOPEFULLY SOMETHING USEFUL
  FTDI_CHECK(ftdi_setflowctrl(&fsc->fc,SIO_RTS_CTS_HS),"RTS/CTS",fsc->fc);//
  // printf("FLOW CONTROL SET\n");
  /**BBMODE WAS SET TO 2 UP THE TOP. However in its place there are a lot of
  other values that could be used. for other purposes. this works for SPI.
  second value(0) is Bitmask "to configure line. HIGH/ON value configures a
  line as output"
  yea... just keep this how it is not sure what its truely up to
  */
  FTDI_CHECK(ftdi_set_bitmode(&fsc->fc,0,BBMODE_SPI), "SET SPI MODE",fsc->fc);
  // printf("BITMODE: %d\n", BBMODE_SPI );
  /**SO after some searching i found out that these DO_READ etc... are hex commands
  yea... its that ECEN 301 stuff, fun times :)...I dont really want to go too deep
  into the micro so "I will just have to accept this, and move on." */
  fsc->wr_cmd = MPSSE_DO_WRITE;
  fsc->rd_cmd = MPSSE_DO_READ | MPSSE_READ_NEG;
  fsc->bitini = BIT_P_CS //CHIP SELECT!!

  //CLEARS usb buffers on chip and internally
  FTDI_CHECK(ftdi_usb_purge_buffers(&fsc->fc), "PURGE", fsc->fc);
  // printf("PURGED USB BUFFERS\n");

  return FTDISPI_ERROR_NONE;
}

/////////SET CLOCK/////////////////////////////////////////////////////////
int spi_setclock(struct spi_context * fsc,
                               uint32_t speed)
{
    uint8_t  buf[3] = { 0, 0, 0 };
    uint32_t div;
    uint32_t base;

    ASSERT_CHECK(!fsc, "CTX NOT INITIALIZED", FTDISPI_ERROR_CTX);
    if (speed > CLOCK_MAX_SPEEDX5 || speed < CLOCK_MIN_SPEED)
    {
        return FTDISPI_ERROR_CLK;
    }
    if (speed > CLOCK_MAX_SPEED)
    {
        /* TODO check if the device can support this */
        base = CLOCK_MAX_SPEEDX5;
    }
    else
    {
        base = CLOCK_MAX_SPEED;
    }
    div = (base / speed) - 1;
    if (div > 0xFFFF)
    {
        return FTDISPI_ERROR_CLK;
    }
    if (base == CLOCK_MAX_SPEEDX5)
    {
        buf[0] = TCK_X5;
        FTDI_CHECK(ftdi_write_data(&fsc->fc, buf, 1), "SET CLK X5", fsc->fc);
    }
    buf[0] = TCK_DIVISOR;
    buf[1] = (div >> 0) & 0xFF;
    buf[2] = (div >> 8) & 0xFF;
    FTDI_CHECK(ftdi_write_data(&fsc->fc, buf, 3), "SET CLK DIV", fsc->fc);
    return FTDISPI_ERROR_NONE;
}

int spi_setloopback(struct spi_context * fsc,int active){
    uint8_t buf[1] = { 0 };

    ASSERT_CHECK(!fsc, "CTX NOT INITIALIZED", FTDISPI_ERROR_CTX);
    if (active)
    {
        buf[0] = LOOPBACK_START;
        FTDI_CHECK(ftdi_write_data(&fsc->fc, buf, 1), "SET LOOP", fsc->fc);
    }
    else
    {
        buf[0] = LOOPBACK_END;
        FTDI_CHECK(ftdi_write_data(&fsc->fc, buf, 1), "SET NO LOOP", fsc->fc);
    }
    return FTDISPI_ERROR_NONE;
}

//SET MODE!!!
//TBH whith you im kinda tired so imma just leave this uncommented for now...
//its not really that inportant to investigate.
int spi_setmode(struct spi_context *fsc,
            int csh,
            int cpol,
            int cpha,
            int lsbfirst,
            int bitmode,
            int gpoini){

  uint8_t buf[3] = {0,0,0};
  ASSERT_CHECK(!fsc, "CTX NOT INITIALIZED", FTDISPI_ERROR_CTX);
  fsc->wr_cmd = MPSSE_DO_WRITE | (bitmode ? MPSSE_BITMODE : 0);
  fsc->rd_cmd = MPSSE_DO_READ  | (bitmode ? MPSSE_BITMODE : 0);
  fsc->bitini = (csh ? BIT_P_CS : 0) | (BIT_P_GX & gpoini);

  if (!cpol){
      /* CLK IDLE = 0 */
      if (!cpha){
          /* W=FE R=RE => NO TX */
          fsc->wr_cmd |= MPSSE_WRITE_NEG;
      }
      else{
          /* W=RE R=FE > RX OPT */
          fsc->rd_cmd |= MPSSE_READ_NEG;
      }
  }
  else{
      /* CLK IDLE == 1 */
      fsc->bitini |=  BIT_P_SK;
      if (!cpha){
          /* W=RE R=FE => NO TX */
          fsc->rd_cmd |= MPSSE_READ_NEG;
      }
      else{
          /* W=FE R=RE => RX OPT */
          fsc->wr_cmd |= MPSSE_WRITE_NEG;
      }
  }
  if (lsbfirst){
      fsc->wr_cmd |= MPSSE_LSB;
      fsc->rd_cmd |= MPSSE_LSB;
  }
  else{
      fsc->wr_cmd &= ~MPSSE_LSB;
      fsc->rd_cmd &= ~MPSSE_LSB;
  }

  buf[0] = SET_BITS_LOW;
  buf[1] = fsc->bitini;
  buf[2] = BIT_DIR;
  FTDI_CHECK(ftdi_write_data(&fsc->fc, buf, 3), "WR INI", fsc->fc);
  if (spi_wait(fsc, BIT_P_CS | BIT_P_GX, fsc->bitini, RETRY_MAX)){
      /* I still don't know why sometime the command must be resent */
      FTDI_CHECK(ftdi_write_data(&fsc->fc, buf, 3), "WR INI", fsc->fc);
  }
  return spi_wait(fsc, BIT_P_CS | BIT_P_GX, fsc->bitini, RETRY_MAX);
}

///////////////////////////////////////////////////////////////////////////////
/////////////////////////////////REALLOC///////////////////////////////////////
static int spi_realloc(struct spi_context * fsc, size_t size)
{
    uint8_t * p;

    if (fsc->memsize < size)
    {
        if (!(p = realloc(fsc->mem, size)))
        {
            return FTDISPI_ERROR_MEM;
        }
        fsc->mem = p;
        fsc->memsize = size;
    }
    return FTDISPI_ERROR_NONE;
}

///////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////WAIT/////////////////////////////////////
static int spi_wait(struct spi_context * fsc, uint8_t mask, uint8_t value, int maxtry)
{
    uint8_t cmd = GET_BITS_LOW;
    uint8_t ret = 0;

    FTDI_CHECK(ftdi_write_data(&fsc->fc, &cmd, 1), "GBLW", fsc->fc);
    FTDI_CHECK(ftdi_read_data(&fsc->fc, &ret, 1), "GBLR", fsc->fc);
    while (maxtry-- && (ret & mask) != (value & mask))
    {
        usleep(RETRY_TIME);
        FTDI_CHECK(ftdi_write_data(&fsc->fc, &cmd, 1), "GBLW", fsc->fc);
        FTDI_CHECK(ftdi_read_data(&fsc->fc, &ret, 1), "GBLR", fsc->fc);
    }

    if ((ret & mask) == (value & mask))
    {
        return FTDISPI_ERROR_NONE;
    }
    else
    {
        return FTDISPI_ERROR_TO;
    }
}

char *int2bin(int a, char *buffer, int buf_size) { //fucntion to output Binay values
    buffer += (buf_size - 1);                      //IT COULD BE WRONG...

    for (int i = 24; i >= 0; i--) {
        *buffer-- = (a & 1) + '0';

        a >>= 1;
    }

    return buffer;
}

int spi_write_read(struct spi_context *fsc,
        const void *wbuf,
        uint16_t wcount,
        void *rbuf,
        uint16_t rcount,
        uint8_t gpo){
  int i, n, r;

  //ASSERT_CHECK(!fsc, "CTX NOT INITIALIZED", FTDISPI_ERROR_CTX);
  //ASSERT_CHECK(!((wbuf && wcount) || (rbuf && rcount)),
               //"NO CMD", FTDISPI_ERROR_CMD);

  //n = wcount + (rcount ? 9 : 6);//clearly im a noob..but (rcount ? 9 : 6) will always = 9??
  //printf("n= %d\n", n);
  //so in our case... n is 12??...hmm come back to this later...
  //ASSERT_CHECK(spi_realloc(fsc, n), "REALLOC", FTDISPI_ERROR_MEM); //mem is now size n

  //WELL... I DONT KNOW WHAT THIS DO
  //i = 0;
  // printf("bitbang = %u\n",fsc->fc.bitbang_mode);



  fsc->mem[0] = 0x80;
  fsc->mem[1] = ((0x0F & (fsc->bitini ^ BIT_P_CS)));
  fsc->mem[2] = 0xFB;//BIT_DIR
  fsc->mem[3] = fsc->wr_cmd;
  fsc->mem[4] = 0x02;//(wcount - 1) & 0xFF
  fsc->mem[5] = 0x200;//((wcount - 1) >> 8) & 0xFF
  memcpy(fsc->mem + 6, wbuf, 3);
  //i += wcount;
  fsc->mem[9] = fsc->rd_cmd;
  fsc->mem[10] = 0x02;
  fsc->mem[11] = 0x200;

  ftdi_write_data(&fsc->fc, fsc->mem, 12); //TAKES TOO LONG......

  ftdi_read_data(&fsc->fc, rbuf, 3); //TAKES TOO LONG......
  //i=0;

  //fsc->mem[0] = SET_BITS_LOW;
  fsc->mem[1] = fsc->bitini;
  //fsc->mem[2] = BIT_DIR;


  ftdi_write_data(&fsc->fc, fsc->mem, 3); //TAKES TOO LONG......
  return 0;//spi_wait(fsc, BIT_P_CS, fsc->bitini, RETRY_MAX);
}

int spi_close(struct spi_context * fsc,int close_ftdi){
    ASSERT_CHECK(!fsc, "CTX NOT INITIALIZED", FTDISPI_ERROR_CTX);
    if (fsc->mem)
    {
        free(fsc->mem);
        fsc->mem = 0;
        fsc->memsize = 0;
    }
    if (close_ftdi)
    {
        ftdi_usb_close(&fsc->fc);
        ftdi_deinit(&fsc->fc);
    }
    return FTDISPI_ERROR_NONE;
}

///////////////////////////////////////////////////////////////////////////////
//////////////////////////THE GOOD FUNCTION////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
int main(int argc, char const *argv[]) {
  struct ftdi_context fc;
  struct spi_context fsc;
  int i;

  //INITIATE DEVICE
  if(ftdi_init(&fc)<0){
    fprintf(stderr, "ftdi_init failed\n");
    return 1;
  }

  //OPEN DEVICE
  i = ftdi_usb_open(&fc,0x0403,0x6014); //CONTEXT, VENDOR, PRODUCT
  if(i==0){
    // printf("USB OPEN: SUCESSFUL\n");
  }
  if(i<0 && i != -5){
    fprintf(stderr, "OPEN: %s\n", ftdi_get_error_string(&fc) );
    exit(-1);
  }

  //OPEN FOR SPI SEE FUNCTION ABOVE :)
  spi_open(&fsc, &fc, INTERFACE_A);
  //SET THE MODE
  spi_setmode(&fsc,1,0,0,0,0,0);
  // printf("SETMODE: SUCESSFUL\n");

  spi_setclock(&fsc,200000);
  // printf("SETCLOCK: SUCESSFUL\n");

  spi_setloopback(&fsc,0);
  // printf("SETLOOPBACK: SUCESSFUL\n");


  //NOW FOR THE BIGGIE!!! THE ONE FUNCTION TO RULE THEM ALL... THE ONE cAUSING SO MANY problems
  char wbuff[3] = {(char)1,(char)128,(char)0};//the nuclear lauch codes... keep it on the low
  unsigned char rbuff[3]; //allocate 3 bytes

  int op = sizeof(rbuff);
  // printf("buff =%d\n", op);

  int count = 0;
  spi_realloc(&fsc, 12);
  while (1) {

    spi_write_read(&fsc,wbuff,3,rbuff,3,0);
    // printf("num: %d\n",  (int *)rbuff); 
    fprintf(stdout, "%d %d %d\n", rbuff[0], rbuff[1], rbuff[2] );


    // ////FOLLOWING CODE JUST PRINTS WHAT IS IN THE  RBUFF/////
    // printf("rubuff size: %d\n",sizeof(rbuff));
    // int *num = (int *)rbuff;
    //
    // int BUFF_SIZE = 25; //65 Because of null terminator, remmebr, if you change it here...
    //                     //CHANGE IT IN int2bin...UPDATE: i changed it to 25 caus now rBuf is only 24bit
    //                     //no idea why it changed
    //
    // char buffer[BUFF_SIZE];
    // buffer[BUFF_SIZE - 1] = '\0';
    //
    // int2bin(*num, buffer, BUFF_SIZE - 1);
    //
    //
    //
    // printf("a = %s \n", buffer);
    // //sleep(0.001);
    // //unsigned int retTime = time(0) + 1;   // Get finishing time.
    // //while (time(0) < retTime); //also its a nice delay
    //
    // count++;

  }

  spi_close(&fsc,1);
  return 0;
}
