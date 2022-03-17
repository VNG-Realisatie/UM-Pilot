// contains the enums that are possible according to specifications.

export const codeWerkEnDenkniveauMinimaalValues: Map<string, string> = new Map<string, string>([
  ["1", "geen basisopleiding"],
  ["2", "basisniveau"],
  ["3", "vmbo-niveau"],
  ["4", "mbo/havo/vwo-niveau"],
  ["5", "hbo-niveau"],
  ["6", "wetenschappelijk onderwijs"],
  ["7", "(nog) niet bekend"],
])

// VUM API switches between either integer or string for the numerical values
export const codeWerkEnDenkniveauMinimaalValuesInteger: Map<number, string> = new Map<number, string>([
  [1, "geen basisopleiding"],
  [2, "basisniveau"],
  [3, "vmbo-niveau"],
  [4, "mbo/havo/vwo-niveau"],
  [5, "hbo-niveau"],
  [6, "wetenschappelijk onderwijs"],
  [7, "(nog) niet bekend"],
])

export const indicatieTwoValues: Map<number, string> = new Map<number, string>([
  [1, "Ja"],
  [2, "Nee"],
])


export const indicatieThreeValues: Map<number, string> = new Map<number, string>([
  [0, "Onbekend"],
  [1, "Ja"],
  [2, "Nee"],
])

export const sollicitatieWijzeValues: Map<string, string> = new Map<string, string>([
  ["1", "Per post"],
  ["2", "Per telefoon"],
  ["3", "Per e-mail"],
  ["4", "Per online sollicitatieformulier"],
])

export const codeTypeArbeidscontractValues: Map<string, string> = new Map<string, string>([
  ["B", "Bepaalde tijd"],
  ["O", "Onbepaalde tijd"]
])

export const codeTypeOvereenkomstValues: Map<string, string> = new Map<string, string>([
  ["01", "Reguliere vacature"],
  ["02", "Leerbaan"],
  ["03", "Vrijwilligerswerk"],
  ["04", "Werkervaringsplaats"]
])

export const rijbewijsValues: Map<string, string> = new Map<string, string>([
  ["A", "A"],
  ["A1", "A1"],
  ["A2", "A2"],
  ["AM", "AM"],
  ["B", "B"],
  ["B+", "B+"],
  ["C1", "C1"],
  ["C", "C"],
  ["D1", "D1"],
  ["D", "D"],
  ["BE", "BE"],
  ["C1E", "C1E"],
  ["CE", "CE"],
  ["D1E", "D1E"],
  ["DE", "DE"],
  ["T", "T"]
])

export const codeRegioStraalValues: Map<number, string> = new Map<number, string>([
  [1, "5 km"],
  [2, "10 km"],
  [3, "15 km"],
  [4, "25 km"],
  [5, "50 km"],
  [6, "75 km"],
  [7, "100 km"],
  [8, "125 km"],
  [9, "150 km"],
])

export const codeNiveauOpleidingValues: Map<number, string> = new Map<number, string>([
  [1, "Basisonderwijs"],
  [2, "VMBO"],
  [3, "HAVO/VWO"],
  [4, "MBO"],
  [5, "HBO/Bachelor"],
  [6, "WO/Master"],
  [9, "Anders"]
])

export const indicatieDiplomaValues: Map<number, string> = new Map<number, string>([
  [0, "Onbekend"],
  [1, "Ja"],
  [2, "Nee"],
  [8, "Niet van toepassing"]
])

export const codeGedragscompetentieValues: Map<number, string> = new Map<number, string>([
  [24100, "Beslissen en activiteiten initiëren"],
  [24101, "Aansturen"],
  [24102, "Begeleiden"],
  [24104, "Aandacht en begrip tonen "],
  [24105, "Samenwerken en overleggen "],
  [24106, "Ethisch en integer handelen"],
  [24107, "Relaties bouwen en netwerken "],
  [24108, "Overtuigen en beïnvloeden"],
  [24109, "Presenteren"],
  [24110, "Formuleren en rapporteren"],
  [24111, "Vakdeskundigheid toepassen"],
  [24112, "Materialen en middelen inzetten"],
  [24113, "Analyseren"],
  [24114, "Onderzoeken"],
  [24115, "Creeren en innoveren"],
  [24116, "Leren"],
  [24118, "Plannen en organiseren"],
  [24119, "Op de behoeften en verwachtingen van de klant richten"],
  [24120, "Kwaliteit leveren"],
  [24121, "Instructies en procedures opvolgen"],
  [24122, "Omgaan met verandering en aanpassen"],
  [24123, "Met druk en tegenslag omgaan"],
  [24124, "Gedrevenheid en ambitie tonen"],
  [24125, "Ondernemend en commercieel handelen"],
  [24126, "Bedrijfsmatig handelen"],
])

export const beheersingGedragscompetentieValues: Map<number, string> = new Map<number, string>([
  [1, "Goed"],
  [2, "Voldoende"],
  [3, "Onvoldoende"],
  [4, "Niet"],
  [5, "Onderzoek noodzakelijk"],
  [9, "Niet van toepassing"],
])


export const taalBeheersingValues: Map<number, string> = new Map<number, string>([
  [0, "Onbekend"],
  [1, "Geen beheersing"],
  [2, "Redelijk"],
  [3, "Goed"],
  [4, "Uitstekend"],
  [8, "Niet van toepassing"]
])

export const codeVervoersmiddelValues: Map<number, string> = new Map<number, string>([
  [1, "Auto"],
  [2, "Bromfiets"],
  [3, "Fiets"],
  [4, "Motor"],
  [5, "Openbaar vervoer"],
  [9, "Anders"],
])

export const codeWebadresValues: Map<number, string> = new Map<number, string>([
  [1, "Webadres werkzoekende profiel"],
  [2, "Webadres CV"],
  [3, "Webadres werkgever"],
  [4, "Webadres online sollicitatieformulier"],
])

export const codeFunctieAdresValues: Map<string, string> = new Map<string, string>([
  ["B", "Briefadres (BRP)"],
  ["W", "Woonadres (BRP)"],
  ["C", "Correspondentieadres"],
  ["L", "Loonaangifte adres"],
  ["A", "Afwijkend woonadres"],
  ["V", "Vestigingsadres"],
  ["E", "Werklocatieadres"],
])

export const codeStatusOpleiding: Map<number, string> = new Map<number, string>([
  [0, "Onbekend"],
  [1, "Succesvol afgerond"],
  [2, "Afgebroken"],
  [3, "Lopend"],
])














