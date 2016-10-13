#include <stdio.h>

int main () {
   int i = 0;
   int j = 0;

   unsigned char buf[3] = "";
   buf[0] = 0;
   buf[1] = 255;
   buf[2] = 0;

   for(i = 0; j < 10; i++ ) {
     fprintf(stdout, "%d %d %d\n", buf[0], buf[1], buf[2] );
    //  printf("FROM C: %d %d %d\n", buf[0], buf[1], buf[2]);

   }

   return 0;
}
