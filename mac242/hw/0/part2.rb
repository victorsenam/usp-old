# Caio Barrocal - 8941130
# Victor Sena Molero - 8941317

def hello (name)
    return "Hello, " + name
end

def starts_with_consonant?(s)
    if (s.length == 0 || (s =~ /^(a|b|c|d|e)/i) != nil)
        return false
    else
        return true
    end
end

def binary_multiple_of_4?(s)
    if ((s =~ /(^(1|0)+0{2}$|^0{1,2}$)/) != nil)
        return true
    else
        return false
    end
end
