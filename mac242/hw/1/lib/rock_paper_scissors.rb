require "pp"

class RockPaperScissors
  @@plays = ["R","P","S"]

  # Exceptions this class can raise:
  class NoSuchStrategyError < StandardError ; end

  def self.winner(player1, player2)
    raise NoSuchStrategyError, "Strategy must be one of R,P,S" unless (player1.length > 1 && @@plays.include?(player1[1]) == true)
    raise NoSuchStrategyError, "Strategy must be one of R,P,S" unless (player2.length > 1 && @@plays.include?(player2[1]) == true)

    hk1 = @@plays.index(player1[1])
    hk2 = @@plays.index(player2[1])

    if (hk2 == hk1+1 || hk2+2 == hk1)
      return player2
    else
      return player1
    end
  end

  def self.tournament_winner(tournament)
    if (@@plays.include?tournament[1])
      return tournament
    else
      return winner(tournament_winner(tournament[0]), tournament_winner(tournament[1]))
    end
  end

end
