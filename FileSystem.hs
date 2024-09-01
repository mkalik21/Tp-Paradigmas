module FileSystem ( FileSystem, nuevoF, departamentosF, anunciosF, agregarAnuncioF, sacarAnuncioF, agregarDepartamentoF, sacarDepartamentoF, anunciosParaF )
  where

import Tipos
import Anuncio

data FileSystem = FS [Departamento] [Anuncio] deriving (Eq, Show)

nuevoF :: FileSystem                                              -- permite obtener un nuevo FileSystem
departamentosF :: FileSystem -> [ Departamento ]                  -- dado un FileSystem retorna los departamentos que incluye
anunciosF :: FileSystem -> [ Anuncio ]                            -- dado un FileSystem retorna los anuncios que incluye
agregarAnuncioF :: Anuncio -> FileSystem -> FileSystem            -- permite agregar un anuncio  
sacarAnuncioF :: Anuncio -> FileSystem -> FileSystem              -- permite eliminar un anuncio
agregarDepartamentoF :: Departamento -> FileSystem -> FileSystem  -- permite agregar un departamento
sacarDepartamentoF :: Departamento -> FileSystem -> FileSystem    -- permite eliminar un departamento
anunciosParaF :: [Departamento] -> FileSystem -> [Anuncio]        -- entrega los anuncios a emitir para un conjunto de departamentos


nuevoF = FS [] []

departamentosF (FS departamentos _ ) = departamentos

anunciosF (FS _ anuncios) = anuncios

agregarAnuncioF anuncio (FS fsDpts fsAnuncios)
  | null (departamentosA anuncio) = FS fsDpts fsAnuncios
  | any (`elem` fsDpts) (departamentosA anuncio) = FS fsDpts (anuncio : fsAnuncios)
  | otherwise = FS fsDpts fsAnuncios


sacarAnuncioF anuncioN (FS departamentos anuncios) = FS departamentos (filter (/= anuncioN) anuncios)

agregarDepartamentoF departamento (FS fsDpts fsAnuncios)
    | departamento `elem` fsDpts = FS fsDpts fsAnuncios  
    | otherwise = FS (departamento : fsDpts) fsAnuncios  


sacarDepartamentoF departamentoN (FS departamentos anuncios) = FS (filter (/= departamentoN) departamentos) anuncios
{-
anunciosParaF departamentos (FS [Departamento] [Anuncio]) = | --Comparar departamentos ingresados con [Departamento] (en FS)
                                                            | --Si hay alguno que coincida, nada
                                                            | --Si no, eliminarlo de la lista de departamentos
                                                            | --Recorrer los anuncios y ver si algun departamento del anuncio coincide con alguno de la lista.
                                                            | --Si coincide, agregarlo a una lista usando pattern matching.
-}
anunciosParaF deps (FS fsDeps fsAnuncios) = filtrarAnuncios fsAnuncios depsFiltrados
  where
    -- Filtramos los departamentos, eliminando aquellos que no existen en el FileSystem
    depsFiltrados = [d | d <- deps, d `elem` fsDeps]

    -- Filtramos los anuncios donde alguno de sus departamentos coincida con los departamentos filtrados
    filtrarAnuncios :: [Anuncio] -> [Departamento] -> [Anuncio]
    filtrarAnuncios [] _ = []
    filtrarAnuncios (a:as) ds
      | any (`elem` ds) (departamentosA a) = a : filtrarAnuncios as ds
      | otherwise = filtrarAnuncios as ds

fs = nuevoF
fs2 = agregarDepartamentoF "Departamento1" fs