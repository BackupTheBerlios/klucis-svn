\version "2.8.8"

%{
@author Hector Berlioz
@piece Symphonie Fantastique
@part Part 2: Un Bal - Valse
@fragment Theme of the Valse
%}

#(set-global-staff-size 14)

\paper{
  line-width = 14\cm
  left-margin = 1\cm
  ragged-bottom = ##t
  ragged-last-bottom = ##t
  between-system-space = 0.5\cm
  system-count = 2
}


\score {
{\new GrandStaff
<<
\new Staff { \clef treble \key a \major \time 3/8  
  \relative c'' { \partial 8*1 cis16\p^\markup{\tiny \sans "Allegro non troppo"}( b) | 
  a(\stemDown gis a b cis d) | 
  e8.( eis16 fis gis) | 
  a8.( gis16 b a) | 
  gis4( fis16) r |
% stave 5
  fis4\sf\>( d16 cis\!) | 
  cis( b) b( a gis a) | 
  b( ais b) e( dis e) | 
  cis8 r8 }
}
\new Staff { \clef bass \key a \major \time 3/8
  { \partial 8*1 r8 | 
  <<a,8\noBeam a,,>> <<a cis' e'>> <<a cis' e'>> | 
  <<a,8\noBeam a,,>> <<a cis' e'>> <<a cis' e'>> | 
  <<a,8\noBeam a,,>> <<cis' e' a'>> <<cis' e' a'>> | 
  <<d\noBeam d,>> \clef treble <<d' fis' a'>> <<d' fis' a'>> |
% stave 5
  \clef bass <<b,8\noBeam b,,>> \clef treble <<d' fis' b'>> <<d' fis' b'>> | 
  \clef bass <<gis8\noBeam gis,>> \clef treble <<gis b e'>> <<gis b e'>> |
  \clef bass <<e8\noBeam e,>> \clef treble <<b d' gis'>> <<b d' gis'>> | 
  \clef bass  <<a8\noBeam a,>> \clef treble <<cis' e' a'>>  }
}
>>
} 
%\midi { \tempo 4=144 }
}