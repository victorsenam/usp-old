#lang racket
(require 2htdp/image)
(require 2htdp/universe)

(define 3f (let ([^ (triangle 40 'solid 'gold)]) (above ^ (beside ^ ^))))
(animate 3f)

