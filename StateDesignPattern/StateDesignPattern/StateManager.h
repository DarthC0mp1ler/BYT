#ifndef _header
#define _header

#include <iostream>
using namespace std;
#pragma warning(disable : 4996)

class GameState;

class StateManager
{
	static StateManager* instance;
	StateManager();
	~StateManager();
public:
	
	static StateManager* GetInstance()
	{
		if (!instance)
		{
			instance = new StateManager();
		}
		return instance;
	}

	static void Destroy()
	{
		delete instance;
	}
	
	GameState* currentState;

	

	void SetCurrentState(GameState*);
	void UpdateAndDraw();
};

class GameState
{
protected:
	char* stateStatus;
	int counter;
public:
	virtual GameState* Update() = 0;
	virtual void Display() const = 0;

};

#endif
