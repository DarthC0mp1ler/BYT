#include "StateMachine.h"
StateMachine::StateMachine()
{
	manager = StateManager::GetInstance();
}

StateMachine::~StateMachine()
{
	StateManager::Destroy();
}

void StateMachine::GameLoop()
{
	int i = 0;
	while (i++ < 30)
	{
		manager->UpdateAndDraw();
		std::this_thread::sleep_for(std::chrono::milliseconds(1500));
	}
}