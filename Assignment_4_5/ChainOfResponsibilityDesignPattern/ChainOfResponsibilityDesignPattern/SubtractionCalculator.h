#include "Calculator.h"
class SubtractionCalculator : Calculator
{
public:
	SubtractionCalculator(Calculator* calc);
	~SubtractionCalculator();

	std::string Calculate(double, double, char);
	std::string Calculate(int, int, char);
};

