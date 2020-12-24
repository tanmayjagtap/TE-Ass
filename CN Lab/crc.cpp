#include <iostream>
#include <string>

using namespace std;

int main()
{

    string msg, crc, encoded = "";

    //Sending Message:

    cout << "Enter the message: ";
    getline(cin, msg);

    cout << "Enter the crc generator: ";
    getline(cin, crc);

    int m = msg.length();
    int g = crc.length();

    encoded += msg;

    for (int i = 0; i < g - 1; i++)
    {
        encoded += '0';
    }

    for (int i = 0; i <= encoded.length() - g;)
    {
        for (int j = 0; j < g; j++)
            encoded[i + j] = encoded[i + j] == crc[j] ? '0' : '1';
        while (i < encoded.length() && encoded[i] != '1')
        {
            i++;
        }

        // for ( ;; i++);
    }
    cout << "Transmitted String : " << msg + encoded.substr(encoded.length() - g + 1) << endl
         << endl;

    //Recieve message

    cout << "Enter message: ";
    getline(cin, encoded);
    cout << "Enter generstor : ";
    getline(cin, crc);

    for (int i = 0; i <= encoded.length() - crc.length();)
    {
        for (int j = 0; j < crc.length(); j++)
            encoded[i + j] = encoded[i + j] == crc[j] ? '0' : '1';

        for (; i < encoded.length() && encoded[i] != '1'; i++)
            ;
    }

    for (char i : encoded.substr(encoded.length() - crc.length()))
    {
        if (i != '0')
        {
            cout << "Error in Coms" << endl;
            return 0;
        }
    }
    cout << "No Error" << endl;

    return 0;
}