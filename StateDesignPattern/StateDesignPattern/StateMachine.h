#include "StateManager.h"
#include <chrono>
#include <thread>
class StateMachine
{
	StateManager * manager;
public:
	StateMachine();
	~StateMachine();

	void GameLoop();
	
};

