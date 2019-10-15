
Z = 5
X = "456"
while Z > -1:
    Y = input("")
    if Y in ('1', '2', '3') or len(Y) > 1:
        break
    X = X + Y
    if Y in ('4', '5', '6'):
        continue
    Z = Z - 1
    
print(Z)