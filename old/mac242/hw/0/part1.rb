# Caio Barrocal - 8941130
# Victor Sena Molero - 8941317

# Método que devolve a soma do vetor a partir do elemnto i
def sum (arr, i=0)
    if (i == arr.length)
        return 0
    end
    return arr[i] + sum(arr, i+1)
end

# Pega o maximo entre dois valores
def getMax (a, b)
    if (a > b)
        return a
    else
        return b
    end
end

# Checa se existe alguma soma de dois itens de arr entre os indices i e j que somem n sendo arr ordenado
def sum_to_n? (arr, n, i = 0, j = arr.length-1)
    if (i == 0 && j == arr.length-1)
        arr = arr.sort
    end

    if (i >= j)
        return false;
    elsif (arr[i] + arr[j] < n)
        return sum_to_n?(arr, n, i+1, j);
    elsif (arr[i] + arr[j] > n)
        return sum_to_n?(arr, n, i, j-1);
    else
        return true;
    end
end

# Método Base da soma dos dois maiores itens de um vetor
def max_2_sum (arr)
    aux = arr.sort
    return sum(aux, getMax(0, aux.length - 2))
end
