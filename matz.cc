#include <stdlib.h>
#include <fstream>
#include <iostream>
#include <string>
#include <math.h>
#include <sstream>
//#include <list>
#include <vector>
using namespace std;

class nonZero
{
    public:
        int colNo;
        double val;
};

int main(int argc, char* argv[])
{
    string line;
    double cutOff;
    vector<nonZero> row;
    vector< vector<nonZero> > rows;

    if(argc > 1 && string(argv[1]) == "-e")
        cutOff = fabs(strtod(argv[2],0));

    int j = 0;
    while(getline(cin, line))
    {

        istringstream lstream(line);
        double val;

        while(lstream>> val)
        {
            if(!(fabs(val) < cutOff))
            {
                if(!val==0)
                {
                    nonZero nz = {j+1, val};
                    row.push_back(nz);
                }
            }
            j++;
        }
        j=0;
        rows.push_back(row);
        row.clear();
    }
    int i;
    int k;
    for(i = 0; i < rows.size(); i++)
    {
        for(k = 0; k < rows[i].size(); k++)
        {
            cout << rows[i][k].colNo << " " << rows[i][k].val << " ";
        }
        cout<<endl;
    }
}
