module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import Link
import City

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT linkList | not (null linkList) = Tun linkList
              | otherwise = error "La lista está vacía"

tunelExtreme :: City -> Tunel -> Bool
tunelExtreme city (Tun linkList) = (connectsL city firstLink && not (connectsL city secondLink)) || (connectsL city lastLink && not (connectsL city secondLastLink))
    where
        firstLink = head linkList
        secondLink = linkList !! 1
        len = length linkList
        secondLastLink = linkList !! (len - 2)
        lastLink = last linkList

connectsT :: City -> City -> Tunel -> Bool 
connectsT city1 city2 tunel@(Tun linkList) | length linkList >= 2 = city1 /= city2 && tunelExtreme city1 tunel && tunelExtreme city2 tunel
                                           | length linkList == 1 = city1 /= city2 && linksL city1 city2 (head linkList)

usesT :: Link -> Tunel -> Bool 
usesT link (Tun linklist) = link `elem` linklist

delayT :: Tunel -> Float 
delayT (Tun linkList) = sum [delayL link | link <- linkList]