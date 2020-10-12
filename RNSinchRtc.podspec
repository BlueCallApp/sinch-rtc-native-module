
Pod::Spec.new do |s|
  s.name         = "RNSinchRtc"
  s.version      = "0.0.13"
  s.summary      = "RNSinchRtc"
  s.description  = <<-DESC
                  RNSinchRtc
                   DESC
  s.homepage     = "https://github.com/tamarinda/sinch-rtc-native-module"
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "author@domain.cn" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/author/RNSinchRtc.git", :tag => "v#{s.version}" }
  s.source_files = "ios/**/*.{h,m,swift}"
  s.swift_version = "4.2"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  