(ns insane-noises.core)
(use 'overtone.live)

(definst tri-wave
  [freq 440 attack 0.01 sustain 0.1 release 0.4 vol 0.4]
  (* (env-gen (lin attack sustain release) 1 1 0 1 FREE)
     (lf-tri freq)
     vol))

(definst sinewave [freq 440]
  (sin-osc freq 0.4))

;; (definst multi-tri []  (+ (tri-wave 220 0.01 0.01 0.4 0.4) (tri-wave 440 0.01 0.1 0.4 0.4)))

(tri-wave)

;; This will loop through a number of harmonically related triangle waves from low to high
;; and then cycle back from high to low.
;; freq argument is starting frequency
(defn triangle-loop [freq rate]
  (let [sequence (range 20 20000)]  
    (at (+ (now) 500) (tri-wave freq 0.01 0.1 0.4 0.4))
    (at (+ (now) 1000) (tri-wave (* freq 2) 0.01 0.1 0.4 0.4))
    (at (+ (now) 1500) (tri-wave (* freq 3) 0.01 0.1 0.4 0.4))
    (at (+ (now) 2000) (tri-wave (* freq 4) 0.01 0.1 0.4 0.4))
    (at (+ (now) 2500) (tri-wave (* freq 3) 0.01 0.1 0.4 0.4))
    (at (+ (now) 3000) (tri-wave (* freq 2) 0.01 0.1 0.4 0.4))
    (at (+ (now) 3500) (tri-wave (* freq 1) 0.01 0.1 0.4 0.4))
    (at (+ (now) 4000) (tri-wave (* freq 0.5) 0.01 0.1 0.4 0.4))))
