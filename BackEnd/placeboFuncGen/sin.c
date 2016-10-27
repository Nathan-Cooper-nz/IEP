#include <stdio.h>
#include <math.h>
#include <unistd.h>

int main(int argc, char const *argv[]) {
  double x = 0;
  int amplitude = 3;
  int count = 0;
  double voltage = 0;
  int sleepTimeMS = 20;

  while (1) {
    if(count == 100){
      amplitude = 7;
    }
    voltage = sin(x) * amplitude;
    printf("%.2f\n", voltage);

    count++;
    x += 1;
    usleep(sleepTimeMS*1000);
  }

  return 0;
}
