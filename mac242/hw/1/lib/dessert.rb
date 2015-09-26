class Dessert
  attr_accessor :name, :calories

  def initialize(name, calories)
    @name = name
    @calories = calories
  end
  def healthy?
    return @calories < 200
  end
  def delicious?
    return true
  end
end

class JellyBean < Dessert
  def initialize(flavor)
    @name = flavor + " jelly bean"
    @calories = 5
  end
  def delicious?
    if (@name =~ /licorice jelly bean/)
      return false
    end
    return super
  end
end
