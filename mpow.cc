#include <stdlib.h>
#include <fstream>
#include <iostream>
#include <string>
#include <math.h>
#include <sstream>
#include <list>
#include <vector>
#include <stdio.h>
using namespace std;

#define MAX(a,b) ((a) > (b) ? (a) : (b))
#define MIN(a,b) ((a) < (b) ? (a) : (b))

class nonZero
{
    friend ostream &operator<<(ostream &, const nonZero &);
    public:
        int colNo;
        double val;

};

ostream &operator<<(ostream &output, const nonZero &nz)
{
    output << nz.colNo << " " << nz.val;
    return output;
}

typedef list<nonZero> sparseRow;
typedef vector<sparseRow> sparseMat;

void multiplyMatrix(sparseMat, sparseMat, sparseMat&);
double dotProduct(const sparseRow&, const sparseRow&);
void readMatrix(sparseMat&, int&);
void transposeMatrix(const sparseMat, sparseMat&);
void printMatrix(const sparseMat);
void matrixPow(const sparseMat, sparseMat&, int);
void matrixPowofX(sparseMat&, sparseMat&);

double cutOff = 0;

int main(int argc, char* argv[])
{
    int exp;
    int column = 0;
    sparseMat rows;
    sparseMat result;

    if(argc > 1 && string(argv[1]) == "-e")
        cutOff = fabs(strtod(argv[2],0));

    if(argc == 2)
    {
        exp = stoi(argv[1]);
    }

    if(exp < 0)
    {
        cout<<"Illegal exponent; exiting."<<endl;
        return (-1);
    }

    readMatrix(rows, column);

    if(exp == 0)
    {
        matrixPowofX(rows, result);
        printMatrix(result);
        return 0;
    }
    if(exp == 1)
    {
        printMatrix(rows);
        return 0;
    }
    if(exp%2 == 1)
    {
        exp--;
        matrixPow(rows, result, exp);
        multiplyMatrix(rows, result, result);
    }
    else
    {
        matrixPow(rows, result, exp);
    }
    printMatrix(result);
}
//         [\\_____________________
//  <o>====[O}____________________/
//         [//

void multiplyMatrix(sparseMat m1, sparseMat m2, sparseMat& result)
{
    result.clear();
    sparseMat trans;
    sparseRow row;
    nonZero nz;
    unsigned int i = 0;
    sparseMat::const_iterator p, q;
    int index2, index1;
    double v = 0;

    transposeMatrix(m2, trans);

    for(i = 0; i < m1.size(); i++)
    {
        result.push_back(row);
    }

    for(p = m1.begin(), index1 = 1; p != m1.end(); p++, index1++)
    {
        for(q = trans.begin(), index2 = 1; q != trans.end(); q++, index2++)
        {
            v = dotProduct(*p, *q);
            nz = {index2, v};
            if(cutOff < fabs(v))
                result[index1-1].push_back(nz);
        }
    }
}
//       _____________//------\\____________
//       \____________{]==<>==[}____________\
//                    \\------//
double dotProduct(const sparseRow& row1, const sparseRow& row2)
{
    sparseRow::const_iterator p = row1.begin(), endp = row1.end();
    sparseRow::const_iterator q = row2.begin(), endq = row2.end();
    double ret = 0;
    int target;

    while(1)
    {
        target = MAX((*p).colNo, (*q).colNo);
        while((*p).colNo < target && p != endp)
        {
            p++;
        }

        if(p == endp)
            break;

        while((*q).colNo < target && q != endq)
        {
            q++;
        }

        if(q == endq)
            break;
        if((*p).colNo == (*q).colNo)
        {
            ret += (*p).val * (*q).val;
            p++;
            if(p == endp)
                break;
        }
    }
    return ret;
}

void readMatrix(sparseMat& rows, int& column)
{
    string line;
    sparseRow row;
    nonZero nz;
    column = 0;

    while(getline(cin, line))
    {

        istringstream lstream(line);
        int col;
        double val;

        while(lstream>> col>> val)
        {
            if(!(fabs(val) < cutOff))
            {
                if(!val==0)
                {
                    if(col > column)
                        column = col;
                    nz = {col, val};
                    row.push_back(nz);
                }
            }
        }
        rows.push_back(row);
        row.clear();
    }
}

void transposeMatrix(const sparseMat rows, sparseMat& trans)
{
    sparseRow row;
    sparseMat::const_iterator p = rows.begin();
    sparseRow::const_iterator q = row.begin();
    int c = 0;
    double v = 0;
    nonZero nz;
    unsigned int i = 0;

    for(i = 0; i < rows.size(); i++)
    {
        trans.push_back(row);
    }

    for(p = rows.begin(), i = 1; p != rows.end(); p++, i++)
    {
        row = *p;
        for(q = row.begin(); q != row.end(); q++)
        {
            c = (*q).colNo;
            v = (*q).val;
            nz = {(int)i, v};
            trans[c-1].push_back(nz);
        }
    }
}

void printMatrix(const sparseMat matrix)
{
    sparseRow row;
    sparseMat::const_iterator p = matrix.begin();
    sparseRow::const_iterator q = row.begin();
    for(p = matrix.begin(); p != matrix.end(); p++)
    {
        row = *p;
        for(q = row.begin(); q != row.end(); q++)
        {
            cout<<*q<<" ";
        }
        cout<<endl;
    }
}

void matrixPow(const sparseMat m1, sparseMat& result, int pow)
{
    if(pow == 2)
    {
        multiplyMatrix(m1, m1, result);
    }
    else
    {
        matrixPow(m1, result, pow/2);
        multiplyMatrix(result, result, result);
    }
}

void matrixPowofX(sparseMat& rows, sparseMat& result)
{
    sparseRow row;
    nonZero nz;

    for(int i = 0; i < rows.size(); i++)
    {
        nz = {i+1, 1};
        row.push_back(nz);
        result.push_back(row);
        row.clear();
    }
}






















