import Point
import City
import Quality
import Link
import Tunel
import Region
import Exception

-- Points
p1 = newP (-5) 2
p2 = newP 6 3
p3 = newP 1 0
p4 = newP (-1) 0
p5 = newP 2 0
p6 = newP (-9) (-8)

-- Cities
c1 = newC "bsas" p1
c2 = newC "sj" p2
c3 = newC "fsa" p3
c4 = newC "cba" p4
c5 = newC "mza" p5
c6 = newC "chaco" p6
c7 = newC "bsas" p2
c8 = newC "punta" p1

-- Qualities
q1 = newQ "low" 1 0.5
q2 = newQ "mid" 2 1.5
q3 = newQ "high" 3 2.5

-- Links
l1 = newL c1 c2 q1
l2 = newL c2 c3 q1
l3 = newL c3 c4 q2
l4 = newL c4 c5 q3
l5 = newL c1 c5 q3

-- Tunel
tun = newT [l1, l2, l3, l4]

-- Nueva Region
region = newR

-- Se agregan ciudades
region1 = foundR region c1
region2 = foundR region1 c2
region3 = foundR region2 c3
region4 = foundR region3 c4
region5 = foundR region4 c5

-- Se enlazan ciudades
region6 = linkR region5 c1 c2 q1
region7 = linkR region6 c2 c3 q1
region8 = linkR region7 c3 c4 q2
region9 = linkR region8 c4 c5 q3
region10 = linkR region9 c1 c5 q3
region11 = tunelR region10 [c3, c4, c5]

finalRegion = tunelR region11 [c1,c2,c3,c4,c5]


-- Tests
allCapacityUsed = availableCapacityForR finalRegion c1 c2 
noCapacityError = tunelR finalRegion [c1,c2,c3]
connectedCities = connectedR finalRegion c1 c5
notConnectedCities = connectedR finalRegion c1 c3
alreadyConnectedCities = tunelR finalRegion [c1,c5]
notAddedCity = linkR finalRegion c1 c6 q2
delayTunc3c5 = delayR finalRegion c3 c5
sameCityName = foundR finalRegion c7
sameCityPoint = foundR finalRegion c8

test = [allCapacityUsed == 0, testF noCapacityError, connectedCities, not notConnectedCities, alreadyConnectedCities == finalRegion, testF notAddedCity, delayTunc3c5 == 10.5, testF sameCityName, testF sameCityPoint]





