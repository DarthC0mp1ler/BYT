#include "Connection.h"


Connection::Connection(char* connectionType, char* serverName, char* serverAddress)
{
	type = connectionType;
	server = serverName;
	address = serverAddress;
}

Connection::~Connection() 
{
	delete[] type;
	delete[] server;
	delete[] address;
}

char* Connection::GetConnectionType()
{
	return type;
}

char* Connection::GetServerAddress()
{
	return address;
}
char* Connection::GetServerName()
{
	return server;
}

void Connection::SetConnectionType(char* connectionType)
{
	if (connectionType)
	{
		type = new char[strlen(connectionType) + 1];
		strcpy(type, connectionType);
	}
	else
	{
		type = '\0';
	}
}

void Connection::SetServerName(char* serverName) 
{
	if (serverName)
	{
		server = new char[strlen(serverName) + 1];
		strcpy(server,serverName);
	}
	else
	{
		type = '\0';
	}
}

void Connection::SetServerAddress(char* serverAddress) 
{
	if (serverAddress)
	{
		address = new char[strlen(serverAddress) + 1];
		strcpy(address, serverAddress);
	}
	else
	{
		type = '\0';
	}
}