#!/usr/bin/env ruby

puts Dir["*.wav"]
Dir["*.wav"].each do |f|
  destination = f.split('_')[2..-1].join.split('.').first

  # puts "mv #{f} #{destination}.wav"
  `mv #{f} #{destination}.wav`
end
