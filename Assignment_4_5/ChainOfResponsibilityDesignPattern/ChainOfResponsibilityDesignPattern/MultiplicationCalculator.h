#include "Calculator.h"
class MultiplicationCalculator : Calculator
{
public:
	MultiplicationCalculator(Calculator* calc);
	~MultiplicationCalculator();

	std::string Calculate(double, double, char);
	std::string Calculate(int, int, char);
};

