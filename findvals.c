#include <omp.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <sys/time.h>
#include "utils.h"

int main(int argc, char **argv)
{
  char *rstr, *tstr;
  rstr = NULL;
  tstr = NULL;
  float ver = 0;

  for(int i = 1; i < argc; i++)
    {
      if(strcmp(argv[i], "-r") == 0)
	{
	  rstr = argv[i+1];
	  i++;
	}
      if(strcmp(argv[i], "-t") == 0)
	{
	  tstr = argv[i+1];
	  i++;
	}
      if(strcmp(argv[i], "-v") == 0)
	{
	  /*vstr = argv[i+1];*/
	  ver = 1;
	  //i++;
	}
    }
  if(rstr == NULL || tstr == NULL || (argc != 5 && argc != 6))
    {
      /* printf("%d %d", rstr, tstr);*/
      fprintf(stderr, "Usage: %s [-v] -r ref -t tol\n", argv[0]);
      exit(1);
    }
  
  struct tm *local;
  time_t start, end;

  time(&start);
  local = localtime(&start);
  printf("# Start time and date: %s", asctime(local));
  

  /* struct timeval tim;
  gettimeofday(&tim, NULL);
  double t1=tim.tv_sec+(tim.tv_usec/1000000.0);
  */
  
  float ref, tol;
  /*ver = strtof(vstr, 0);*/
  ref = strtof(rstr, 0);
  tol = strtof(tstr, 0);
  if(tol < 0.0)
    tol *= -1;

  int rCnt, cCnt;
  
  fscanf(stdin, "%d", &rCnt);
  fscanf(stdin, "%d", &cCnt);
  
  float** rows = (float** ) malloc(rCnt * sizeof(float *));
  if(rows == 0)
    {
      fprintf(stderr, "Couldn't alocate sufficient space.\n");
      exit(1);
    }
  for(int i = 0; i < rCnt; i++)
    {
      float* row = (float* ) malloc(cCnt * sizeof(float));
      if(row == 0)
        {
          fprintf(stderr, "Couldn't alocate sufficient row space.\n");
          exit(1);
        }
      rows[i] = row;
    }

  for(int i = 0; i < rCnt; i++)
    {
      for(int j = 0; j < cCnt; j++)
        {
          fscanf(stdin, "%f", &rows[i][j]);
        }
    }
  
struct timeval tim;
  gettimeofday(&tim, NULL);
  double t1=tim.tv_sec+(tim.tv_usec/1000000.0);

  
  int i, j;
  int hits = 0;
  
#pragma omp parallel for collapse(2)
  
  for(i = 0; i < rCnt; i++)
    {
      for(j = 0; j < cCnt; j++)
        {
          if(approxEqual(rows[i][j], ref, tol) == 1)
            {
	    /*if(ver == 1)
		{
		  fprintf(stdout, "r=%d, c=%d: %.6f\n", i, j, rows[i][j]);
		} */
	      if(ver == 1)
		{
                  fprintf(stdout, "r=%d, c=%d: %.6f (thread= %d)\n",
			  i, j, rows[i][j], omp_get_thread_num());
		}
	      #pragma omp atomic
	      hits ++;
            }
        }
    }
  
  
  fprintf(stdout, "Found %d approximate matches.\n", hits);

  /* time(&end);
  local = localtime(&end);
  printf("# End time and date: %s", asctime(local));
  */

  gettimeofday(&tim, NULL);
  double t2=tim.tv_sec+(tim.tv_usec/1000000.0);
  printf("Search time: %.6lf(s) \n", t2-t1);

  exit(0);
}
