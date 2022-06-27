string = "ZDPUN{Jhlzhy_PZ_clyf_LHZF}"
string = string.lower()
print(string)
result = ""
for i in range(1, 26):
    for char in string:
        if (ord('a') <= ord(char)) and (ord(char) <= ord('z')):
            if ord(char) - 7 < ord('a'):
                result += chr(ord(char) - i + 26)
            else:
                result += chr(ord(char) - i)
        else:
            result += char
    print(result)
    result = ""





