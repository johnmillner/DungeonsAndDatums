
tmp = "||1d4-1"
negative = False
attack = tmp.split('|')
atkNumOfDice = attack[2].split('d')
atkSizeOfDice = atkNumOfDice[1]
if '-' in atkSizeOfDice:
	atkSizeOfDice = atkNumOfDice[1].split('-')
	negative = True
else:
	atkSizeOfDice = atkNumOfDice[1].split('+')

atkName = attack[0]

print attack
if attack[1] is '':
	atkAvgDmg = -1
else:
	atkAvgDmg = int (attack[1])

if len(atkSizeOfDice) is 1:
	atkModifier = 0
else:
	if negative is True:
		atkModifier = int(atkSizeOfDice[1])
		atkModifier = -atkModifier
	else:
		atkModifier = int(atkSizeOfDice[1])
		
atkSizeOfDice = int(atkSizeOfDice[0])
atkNumOfDice = int(atkNumOfDice[0])

print atkName, atkAvgDmg, atkNumOfDice, atkSizeOfDice, atkModifier