#include <stdio.h>
#include <stdlib.h>

int flipChar(char c)
{
  int pos;
  if ('a' <= c && c <= 'z')
    {
    pos = c - 'a';
    c = 'z' - pos;
    }
  else if ('A' <= c && c <= 'Z')
    {
      pos = c - 'A';
      c = 'Z' - pos;
    }
  else if ('0' <= c && c <= '9')
    {
      pos = c - '0';
      c = '9' - pos;
    }
  return c;
}


int approxEqual(float val, float refval, float tol)
{
  return (refval-tol <= val && val <= refval+tol);
}
