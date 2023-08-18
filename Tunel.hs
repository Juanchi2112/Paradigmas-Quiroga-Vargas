module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import Link
import City
import GHC.Exts.Heap (GenClosure(link))
import GHC.Num.Backend (c_mpn_add_1)

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT linkList = Tun linkList

getFirst :: [(Link)] -> City
getFirst linkList = fst(tupleLinks (head linkList))

getLast :: [(Link)] -> City
getLast linkList = snd(tupleLinks (last linkList))

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 (Tun linkList) = ((city1 == getFirst linkList ) || (city1 == getLast linkList))&&((city2 == getFirst linkList) || (city2 == getLast linkList))

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun linklist) = elem link linklist

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delay (Tun []) = 0
delayT (Tun (l:linkList)) = delayL l + delayT (Tun linkList) 
