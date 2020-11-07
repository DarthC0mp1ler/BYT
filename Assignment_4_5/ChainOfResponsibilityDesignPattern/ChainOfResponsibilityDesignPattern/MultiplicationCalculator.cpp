#include "MultiplicationCalculator.h"


MultiplicationCalculator::MultiplicationCalculator(Calculator* calc)
{
	successor = calc;
}

MultiplicationCalculator::~MultiplicationCalculator()
{
	delete successor;
}

std::string MultiplicationCalculator::Calculate(double num1, double num2, char command)
{
	if (command == 'm' || command == 'M')
	{
		return std::to_string(num1) + " * " + std::to_string(num2) + " = " + std::to_string(num1 * num2) + "\n";
	}
	else if (successor != 0)
		return "\tPass\n" + successor->Calculate(num1, num2, command);
	else return "Cannot process";
}
std::string MultiplicationCalculator::Calculate(int num1, int num2, char command)
{
	if (command == 'm' || command == 'M')
	{
		return std::to_string(num1) + " * " + std::to_string(num2) + " = " + std::to_string(num1 * num2) + "\n";
	}
	else if(successor != 0)
		return "\tPass\n" + successor->Calculate(num1, num2, command);
	else return "Cannot process";
}