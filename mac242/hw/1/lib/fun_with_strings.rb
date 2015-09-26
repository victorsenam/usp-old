module FunWithStrings
  def palindrome?
    if ( self.length <= 1)
      return true
    elsif ( self[0].downcase < 'a' || self[0].downcase > 'z')
      return self[1..-1].palindrome?
    elsif ( self[-1].downcase < 'a' || self[-1].downcase > 'z')
      return self[0..-2].palindrome?
    elsif ( self[0].downcase != self[-1].downcase )
      return false
    else
      return self[1..-2].palindrome?
    end
  end
  def count_words
    ret = {}
    ret.default = 0
    str = self.gsub(/[^a-zA-Z ]/, '')
    str = str.gsub(/ {2,}/, ' ')
    str = str.gsub(/^ +/, '')
    str.downcase!

    str.split(' ').each do |pre|
      ret[pre]
      ret[pre] = ret[pre]+1
    end

    return ret
  end
  def anagram_groups
  # Aparentemente o erro está no teste, ele nao consegue dar sort no vetor ou algo do tipo
    ret = []
    hash = {}
    str = self.gsub(/[^a-zA-Z ]/, '')
    str = str.gsub(/ {2,}/, ' ')
    str = str.gsub(/^ +/, '')
    str.downcase!

    str.split(' ').each do |pre|
      aux = pre.split('').sort.join
      if (hash.has_key?(aux))
        hash[aux].push(pre)
      else
        hash[aux] = [pre]
      end
    end

    puts " =a= "
    hash.each do |arr|
      puts arr
      ret.push(arr)
    end
    puts " =b= "
    puts hash
    puts " =c= "
    puts ret
    puts " =d= "

    return ret
  end
end

# make all the above functions available as instance methods on Strings:

class String
  include FunWithStrings
end
