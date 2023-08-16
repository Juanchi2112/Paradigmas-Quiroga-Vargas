module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import Link
import City

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT linkList = Tun linkList

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 (Tun linkList) = ((city1 == fst(tupleLinks (head linkList))) || (city1 == snd(tupleLinks (last linkList))))&&((city2 == fst(tupleLinks (head linkList))) || (city2 == snd(tupleLinks (last linkList))))

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun linklist) = elem link linklist

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delay (Tun []) = 0
delayT (Tun (l:linkList)) = delayL l + delayT (Tun linkList) 
