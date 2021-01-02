import random
a = list(map(int,input("\nEnter the numbers in the frame in a sequence with blank spaces between two numbers: ").strip().split()))
n = int(input("Enter window size: "))
count = int(len(a) / 4) 
i = 0
while i < len(a):
    j = 0 
    for _ in range(n) :
        if i < len(a):
            print("Sent frame " + str(a[i]))
            i,j = i + 1,j+1
    i = i - j 
    for _ in range(n) :
        x = random.randint(0,1)
        if x == 0 and count != 0 and i < len(a):
            print("Frame " + str(a[i]) + " not recieved.Transmit the frame again")
            count = count - 1 
            break 
        elif i < len(a) :
            print ("Acknowledgment recieved for Frame " + str(a[i]))
            i = i + 1 

# OutPut
# Enter the numbers in the frame in a sequence with blank spaces between two numbers: 1 2 3 4 5 6 7 8 9
# Enter window size: 3
# Sent frame 1
# Sent frame 2
# Sent frame 3
# Frame 1 not recieved.Transmit the frame again
# Sent frame 1
# Sent frame 2
# Sent frame 3
# Acknowledgment recieved for Frame 1
# Frame 2 not recieved.Transmit the frame again
# Sent frame 2
# Sent frame 3
# Sent frame 4
# Acknowledgment recieved for Frame 2
# Acknowledgment recieved for Frame 3
# Acknowledgment recieved for Frame 4
# Sent frame 5
# Sent frame 6
# Sent frame 7
# Acknowledgment recieved for Frame 5
# Acknowledgment recieved for Frame 6
# Acknowledgment recieved for Frame 7
# Sent frame 8
# Sent frame 9
# Acknowledgment recieved for Frame 8
# Acknowledgment recieved for Frame 9
