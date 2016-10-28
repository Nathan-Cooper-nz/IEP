#include <stdio.h>
#include <math.h>
#include <unistd.h>

int main(int argc, char const *argv[]) {
  double x = 0;
  int amplitude = 7;
  int count = 0;
  double voltage = 0;
  int sleepTimeMS = 30;

  while (1) {
    voltage = sin(180*x/3.14159)>=0?amplitude:-1*amplitude;
    printf("%.2f\n", voltage * 1000.0);

    count++;
    x += 1;
    usleep(sleepTimeMS*1000);
  }

  return 0;
}
