module Prompter ( Prompter, nuevoP, archivosP, departamentosP, configurarP, anunciosP, showP, avanzarP, duracionP )
    where

import Tipos
import Anuncio
import FileSystem
import Debug.Trace (trace)

data Prompter = Pro FileSystem [Departamento] Int deriving (Eq, Show)

nuevoP :: FileSystem -> Prompter                       -- permite obtener un nuevo Prompter en base a un FileSystem
archivosP :: Prompter -> FileSystem                    -- dado un prompter retorna su fileSystem
departamentosP :: Prompter -> [Departamento]           -- dado un prompter retorna sus departamentos
configurarP :: Prompter -> [Departamento] -> Prompter  -- prepara el prompter para emitir los anuncios en los departementos indicados
anunciosP :: Prompter -> [Nombre]                      -- entrega la lista de nombres de anuncios configurados
showP :: Prompter -> Anuncio                           -- muestra el anuncio actual 
avanzarP :: Prompter -> Prompter                       -- pasa al siguiente anuncio
duracionP :: Prompter -> Duracion                      -- indica la duracion total de los anuncios configurados  

nuevoP filesystem = Pro filesystem (departamentosF filesystem) 1

archivosP (Pro fs _ _)  = fs

departamentosP (Pro _ departamento _) = departamento


configurarP (Pro fs prompterDpts _) dptsUsuario =
    let
        -- Filtramos la lista de departamentos ingresada para que solo contenga los que están en el FileSystem
        dptsFiltrados = filter (`elem` departamentosF fs) dptsUsuario

        -- Filtramos los anuncios del FileSystem que corresponden a los departamentos filtrados
        anunciosValidos = filter (any (`elem` dptsFiltrados) . departamentosA) (anunciosF fs)
        fsRestante = foldr sacarAnuncioF fs anunciosValidos  -- Eliminamos los anuncios válidos del FileSystem original

        -- Creamos un nuevo Prompter con el FileSystem modificado y el número de anuncios seleccionados
        nuevoPrompter = Pro (agregarAnuncios fsRestante anunciosValidos) prompterDpts (length anunciosValidos)

    in
        nuevoPrompter

-- Función auxiliar para agregar anuncios a un FileSystem sin usar el constructor 'FS'
agregarAnuncios = foldr agregarAnuncioF


-- Devuelve los nombres de los anuncios filtrados en el prompter
anunciosP (Pro fs dpts _) = map nombreA (anunciosFiltrados fs dpts)

-- Helper function to get filtered anuncios based on dpts
anunciosFiltrados fs dpts = filter (aplicaA dpts) (anunciosF fs)

-- Muestra el anuncio actual basado en el índice solo si está en la lista filtrada
showP (Pro fs _ idx)
  | idx < 1 = error "Índice negativo o cero no es válido."
  | idx > length (anunciosFiltrados fs (departamentosP (Pro fs [] idx))) = error "Índice fuera de rango."
  | otherwise = anunciosF fs !! (idx - 1)

-- Avanza al siguiente anuncio filtrado en la lista
avanzarP (Pro fs dpts index) =
    let anunciosFiltradosList = anunciosFiltrados fs dpts
        newIndex = index + 1
    in if newIndex >= length anunciosFiltradosList
       then Pro fs dpts index  -- Si llegamos al final de la lista filtrada, mantenemos el índice actual
       else Pro fs dpts newIndex

-- Calcula la duración total de los anuncios filtrados

duracionP (Pro fs dpts _) = sum $ map duracionA (anunciosFiltrados fs dpts)

{- 
Comparamos la lista de departamentos que ingresa el usuario con la lista de departamentos que tiene el prompter
Eliminamos (de la lista de dpts ingresada) todos los dpts que no se encuentren en el prompter
Con la nueva lista que unicamente tiene los dpts que se encuentran en el filesystem, recorremos los anuncios para ver si alguno corresponde con algun dpto de la lista
Si corresponde con un dpto de la lista, lo agrego a una nueva lista y borro ese anuncio del filesystem
Una vez que recorri todos los anuncios, calculo el largo de la nueva lista de anuncios y agrego la lista entera al principio del filesystem, dejando los no usados al final.
Una vez hecho esto, le paso el filesystem ordenado al prompter
Empiezo a mostrar los anuncios desde el principio, y voy avanzando de a uno 
Una vez que llego al largo de la lista, dejo de mostrar y muestro un msj de "Ese es el final de los anuncios"
-}