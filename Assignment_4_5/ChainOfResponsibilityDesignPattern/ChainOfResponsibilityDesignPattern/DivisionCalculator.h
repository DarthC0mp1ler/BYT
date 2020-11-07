#include "Calculator.h"
class DivisionCalculator : Calculator
{
public:
	DivisionCalculator(Calculator* calc);
	~DivisionCalculator();

	std::string Calculate(double, double, char);
	std::string Calculate(int, int, char);
};

