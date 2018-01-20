
tmp = "ColdBreath||10d8+2"
attack = attack.split('|')
atkNumOfDice = attack[2].split('d')
atkSizeOfDice = atkNumOfDice[1].split('+')

atkName = attack[0]


if attack[1] is '':
	atkAvgDmg = None
else:
	atkAvgDmg = int (attack[1])

if len(atkSizeOfDice) is 1:
	atkModifier = 0
else:
	atkModifier = int(atkSizeOfDice[1])
atkSizeOfDice = int(atkSizeOfDice[0])
atkNumOfDice = int(atkNumOfDice[0])

print atkName, atkAvgDmg, atkNumOfDice, atkSizeOfDice, atkModifier