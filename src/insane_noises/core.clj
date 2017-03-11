(ns insane-noises.core)
(use 'overtone.live)

(definst tri-wave-adsr
  [freq 440 attack 0.01 sustain 0.1 release 0.4 vol 0.4]
  (* (env-gen (lin attack sustain release) 1 1 0 1 FREE)
     (lf-tri freq)
     vol))

(definst sinewave [freq 440]
  (sin-osc freq 0.4))

(definst modulated-tri [freq 440 lfo-freq 10]
  (* (lf-tri freq) (lf-tri lfo-freq)))

;; (definst multi-tri []  (+ (tri-wave-adsr 220 0.01 0.01 0.4 0.4) (tri-wave-adsr 440 0.01 0.1 0.4 0.4)))

(tri-wave-adsr)

;; This will loop through a number of harmonically related triangle waves from low to high
;; and then cycle back from high to low.
;; freq argument is starting frequency
(defn triangle-loop [freq delay]
  (at (+ (now) (* delay 1)) (tri-wave-adsr freq 0.01 0.1 0.4 0.4))
  (at (+ (now) (* delay 2)) (tri-wave-adsr (* freq 2) 0.01 0.1 0.4 0.4))
  (at (+ (now) (* delay 3)) (tri-wave-adsr (* freq 3) 0.01 0.1 0.4 0.4))
  (at (+ (now) (* delay 4)) (tri-wave-adsr (* freq 4) 0.01 0.1 0.4 0.4))
  (at (+ (now) (* delay 5)) (tri-wave-adsr (* freq 3) 0.01 0.1 0.4 0.4))
  (at (+ (now) (* delay 6)) (tri-wave-adsr (* freq 2) 0.01 0.1 0.4 0.4))
  (at (+ (now) (* delay 7)) (tri-wave-adsr (* freq 1) 0.01 0.1 0.4 0.4))
  (at (+ (now) (* delay 8)) (tri-wave-adsr (* freq 0.5) 0.01 0.1 0.4 0.4))
  (apply-at (+ (now) (* delay 8)) #'triangle-loop [freq delay]))

;; (triangle-loop 440 100)

(defn triangle-rand [freq delay]
  (at (now) (tri-wave-adsr freq 0.01 0.1 0.4 0.4))
  (at (+ (now) (* delay (rand-int 7))) (tri-wave-adsr freq 0.01 0.1 0.4 0.4))
  (at (+ (now) (* delay (rand-int 7))) (tri-wave-adsr (* freq 2) 0.01 0.1 0.4 0.4))
  (at (+ (now) (* delay (rand-int 7))) (tri-wave-adsr (* freq 3) 0.01 0.1 0.4 0.4))
  (at (+ (now) (* delay (rand-int 7))) (tri-wave-adsr (* freq 4) 0.01 0.1 0.4 0.4))
  (at (+ (now) (* delay (rand-int 7))) (tri-wave-adsr (* freq 3) 0.01 0.1 0.4 0.4))
  (at (+ (now) (* delay (rand-int 7))) (tri-wave-adsr (* freq 2) 0.01 0.1 0.4 0.4))
  (at (+ (now) (* delay (rand-int 7))) (tri-wave-adsr (* freq 1) 0.01 0.1 0.4 0.4))
  (at (+ (now) (* delay (rand-int 7))) (tri-wave-adsr (* freq 0.5) 0.01 0.1 0.4 0.4))
  (apply-at (+ (now) (* delay 8)) #'triangle-rand [freq delay]))

