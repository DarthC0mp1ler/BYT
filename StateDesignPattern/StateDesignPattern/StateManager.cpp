#include "StateManager.h"
#include "introState.h"
#include "menuState.h"

StateManager* StateManager::instance = 0;

StateManager::StateManager()
{
	currentState =(GameState*) new IntroState();
}

StateManager::~StateManager()
{
	//
}


void StateManager::SetCurrentState(GameState* state)
{
	currentState = state;
}

void StateManager::UpdateAndDraw() 
{
	GameState* state = currentState->Update();
	if (state) 
	{
		SetCurrentState(state);
	}
	currentState->Display();
}