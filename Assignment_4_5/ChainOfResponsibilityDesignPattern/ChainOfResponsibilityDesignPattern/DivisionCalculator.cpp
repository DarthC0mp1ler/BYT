#include "DivisionCalculator.h"

DivisionCalculator::DivisionCalculator(Calculator* calc)
{
	successor = calc;
}

DivisionCalculator::~DivisionCalculator()
{
	delete successor;
}

std::string DivisionCalculator::Calculate(double num1, double num2, char command)
{
	if (command == 'd' || command == 'D')
	{
		return std::to_string(num1) + " / " + std::to_string(num2) + " = " + std::to_string(num1 / num2) + "\n";
	}
	else if (successor != 0)
		return "\tPass\n" + successor->Calculate(num1, num2, command);
	else return "Cannot process";
}
std::string DivisionCalculator::Calculate(int num1, int num2, char command)
{
	if (command == 'd' || command == 'D')
	{
		return std::to_string(num1) + " / " + std::to_string(num2) + " = " + std::to_string(num1 / num2) + "\n";
	}
	else if (successor != 0)
		return "\tPass\n" + successor->Calculate(num1, num2, command);
	else return "Cannot process";
}