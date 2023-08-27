import Point
import City
import Quality
import Link
import Tunel
import Region

p1 = newP 5 2
p2 = newP 6 3
p3 = newP 1 0
p4 = newP 10 5
p5 = newP 4 1

c1 = newC "bsas" p1
c2 = newC "sj" p2
c3 = newC "fsa" p3
c4 = newC "cba" p4
c5 = newC "mza" p5

q1 = newQ "low" 1 0.5
q2 = newQ "mid" 2 1.5
q3 = newQ "high" 3 2.5

l1 = newL c1 c2 q1
l2 = newL c2 c3 q1
l3 = newL c3 c4 q2
l4 = newL c4 c5 q3
l5 = newL c1 c5 q3

tun = newT [l1, l2, l3, l4]

region = newR

region1 = foundR region c1
region2 = foundR region1 c2
region3 = foundR region2 c3
region4 = foundR region3 c4
region5 = foundR region4 c5

region6 = linkR region5 c1 c2 q1
region7 = linkR region6 c2 c3 q1
region8 = linkR region7 c3 c4 q2
region9 = linkR region8 c4 c5 q3
region10 = linkR region9 c1 c5 q3

region11 = tunelR region10 [c1,c2,c3,c4,c5]
regionTest = tunelR region11 [c1,c2,c3]

