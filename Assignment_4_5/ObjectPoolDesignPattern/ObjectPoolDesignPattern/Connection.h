#include <string>
#pragma warning(disable : 4996)

class Connection
{
	char* type;
	char* server;
	char* address;

public:
	
	Connection(char*, char*, char*);
	~Connection();

	char* GetConnectionType();
	char* GetServerAddress();
	char* GetServerName();

	void SetConnectionType(char*);
	void SetServerName(char*);
	void SetServerAddress(char*);

};

