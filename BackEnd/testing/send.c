#include <stdio.h>

int main () {
   int i = 0;
   int j = 0;

   char buf[3] = "";
   buf[0] = 0;
   buf[1] = 129;
   buf[2] = 1;

   for(i = 0; j < 10; i++ ) {
      fprintf(stdout, "%c%c%c\n", buf[0], buf[1], buf[2] );
   }

   return 0;
}
