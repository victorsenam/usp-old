class MoviesController < ApplicationController

  def show
    id = params[:id] # retrieve movie ID from URI route
    @movie = Movie.find(id) # look up movie by unique ID
    # will render app/views/movies/show.<extension> by default
  end

  def index
    query = Movie
    
    # defining order
    if params.has_key?:sort
      session[:sort] = params[:sort]
    elsif session.has_key?:sort
      params[:sort] = session[:sort]
    else
      params[:sort] = []
    end
    @sorting = params[:sort]
      
    if (['title','release_date'].include?@sorting)
      query = query.order(@sorting)
    end

    # defining ratings
    if params.has_key?:ratings
      session[:ratings] = params[:ratings]
    elsif session.has_key?:ratings
      params[:ratings] = session[:ratings]
    else
      params[:ratings] = {}
    end
    @ratings = params[:ratings].keys
    if (@ratings != [])
      query = query.where({ rating: @ratings })
    end
    
    # concluding
    @movies = query.all
    @all_ratings = []
    Movie.select('rating').uniq.each do |ret|
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
