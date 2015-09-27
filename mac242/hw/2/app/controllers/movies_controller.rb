class MoviesController < ApplicationController

  def show
    id = params[:id] # retrieve movie ID from URI route
    @movie = Movie.find(id) # look up movie by unique ID
    # will render app/views/movies/show.<extension> by default
  end

  def index
    query = Movie
    
    ## GETTING MOVIES
    # defining the order to print
    if (params[:sorting] == 'title')
      query = query.order("title")
    elsif (params[:sorting] == 'release_date')
      query = query.order("release_date")
    end

    # defining wheres
    if params.has_key?:ratings
      query = query.where({ rating: (params[:ratings].keys) })
    else
      query = query.all
    end
    
    ## TEMPLATE INFOS
    @movies = query
    @sorting = params[:sorting]
    @all_ratings = []
    Movie.select(:rating).uniq.each do |ret|
      @all_ratings.push(ret.rating)
    end
  end

  def new
    # default: render 'new' template
  end

  def create
    @movie = Movie.create!(params[:movie])
    flash[:notice] = "#{@movie.title} was successfully created."
    redirect_to movies_path
  end

  def edit
    @movie = Movie.find params[:id]
  end

  def update
    @movie = Movie.find params[:id]
    @movie.update_attributes!(params[:movie])
    flash[:notice] = "#{@movie.title} was successfully updated."
    redirect_to movie_path(@movie)
  end

  def destroy
    @movie = Movie.find(params[:id])
    @movie.destroy
    flash[:notice] = "Movie '#{@movie.title}' deleted."
    redirect_to movies_path
  end

end
