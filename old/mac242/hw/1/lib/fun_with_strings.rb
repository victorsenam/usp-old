require 'pp'

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
  def count_words (clear = false)
    ret = {}
    ret.default = 0
    str = self
    if (!clear)
      str = self.gsub(/[^a-zA-Z ]/, '')
      str = str.gsub(/ {2,}/, ' ')
      str = str.gsub(/^ +/, '')
      str.downcase!
    end

    str.split(' ').each do |pre|
      ret[pre]
      ret[pre] = ret[pre]+1
    end

    return ret
  end
  def anagram_groups
    counted = {}
    str = self.gsub(/[^a-zA-Z ]/, '')
    str = str.gsub(/ {2,}/, ' ')
    str = str.gsub(/^ +/, '')
    str.downcase!
    
    words = str.split(' ')

    words.each do |it|
      chars = it.split('').sort.join('')
      if counted.has_key? chars
        counted[chars].push(it)
      else
        counted[chars] = [it]
      end
    end

    ret = []
    counted.each do |it|
      ret.push(it[1])
    end

    return ret
  end
end

# make all the above functions available as instance methods on Strings:

class String
  include FunWithStrings
end
