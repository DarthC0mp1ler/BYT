#include "stateMachine.h"


int main()
{
	StateMachine* sm = new StateMachine();
	sm->GameLoop();

	delete sm;
}