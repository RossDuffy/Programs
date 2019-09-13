output = ""
i = 1
while(i <= 100):
	m3 = i%3
	m5 = i%5
	if m3 == 0:
		output += "Fizz"
	if m5 == 0:
		output += "Buzz"
	if m3 != 0 and m5 != 0:
		output += str(i)
	print(output)
	output = ""
	i+=1
else:
	print("\nFinished")