#ifndef __CALC_H_
#define __CALC_H_

#include <iostream>
#include<string>

class Calculator
{
protected:
	Calculator* successor;
public :
	
	virtual std::string  Calculate(double,double,char) = 0;
	virtual std::string  Calculate(int,int,char) = 0;
};

#endif

