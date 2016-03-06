# Caio Barrocal - 8941130
# Victor Sena Molero - 8941317

class BookInStock
    def checkValidity
        if (@isbn == nil || @isbn.length == 0)
            raise ArgumentError, "Empty ISBN", caller
        elsif (@price < 0)
            raise ArgumentError, "Negative Price", caller
        end
    end

    def initialize (isbn, price)
        @isbn = isbn
        @price = price
        checkValidity
    end

    def setISBN (isbn)
        @isbn = isbn
        checkValidity
    end

    def setPrice (price)
        @price = price
        checkValidity
    end

    def getISBN
        return @isbn
    end

    def getPrice
        return @price
    end

    def getISBN
        return @isbn
    end

    def price_as_string
        res = @price.to_s

        if ((res =~ /\./) == nil)
            res += '.00'
        elsif ((res =~ /\.\d$/) != nil)
            res += '0'
        end

        return '$' + res[/^\d+\.\d{2}/]
    end
end
