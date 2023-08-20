import Point
import City
import Quality
import Link
import Tunel

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
