Kod źródłowy został podzielony na następujące pakiety:
app - zawiera główną klasę programu wraz z metodą main oraz klasy stanu aplikacji
app.constants - zawiera stałe programowe dla ujednolicenia komunikatów oraz stałych wartości w aplikacji
app.core - zawiera główne funkcjonalności aplikacji
app.gui - zawiera elementy interfejsu graficznego

app:
- Main - uruchamia klasę ImageEditEngine
- ImageEditEngine - klasa służąca do zarządzania stanem aplikacji.
Przechowuje aktualnie wybrany obrazek (originalImage, originalImageBuffer, imageFile) jako statyczne zmienne
w celu łatwiejszego dostępu z każdej klasy aplikacji. Dodatkowo przechowuje stan fileChooseDialog, który odpowiada
za wybór pliku z dysku komputera. Dzięki temu po wyborze pliku programista ma do niego dostęp w każdym miejscu apikacji.
Sam konstruktor tej klasy uruchamia interfejs graficzny (ImageGUI).
Metoda openImage wywołuje okienko dialogowe, z którego użytkownik może wybrać plik przeznaczony do podglądu i edycji.

app.constants
- Messages - aktualnie zawiera tylko komunikaty o błędach

app.core
- Histogram - zawiera mechanizm, który analizuje obrazek i zbiera dane potrzebne do stworzenia histogramu

app.gui
- ImageGUI - tworzy główny interfejs aplikacji. Jako swoje zmienne składowe zawiera:
menuBar - menu znajdujące się w aplikacji
imagePanel - część interfejsu obsługująca aktualnie edytowany obrazek
imageLabel - obrazek wyświetlany jest w formie labelki, gdyż pozwala na ustawienie jej jako pliku graficznego
toolsPanel - panel odpowiadający za wywoływanie narzędzi (np. histogramu)
Konstruktor tej klasy inicjuje wyżej opisane komponenty i skleja je ze sobą, tworząc spójny interfejs

- ImageMenuGUI - "menu obrazka", jest to część menu (zakładka) odpowiadająca otwarciu, zapisaniu oraz duplikacji obrazka

- Lab1MenuGUI - menu laboratorium pierwszego, zawiera elementy odpowiadające za utworzenie histogramu i wyświetlenie histogramu
Do utworzenia histogramu wykorzystana została biblioteka JFreeChat. Przycisk histogramu wywołuje klasę "Histogram", która analizuje
wybrany obrazek i wyciąga informacje na temat rozkładu poszczególnych barw, następnie dodaje te dane do danych wyświetlanych
na wykresie.

- MenuGUI - Kontener zbierający w sobie elementy menu (w tym przypadku ImageMenuGUI oraz Lab1MenuGUI) i tworzący z tych elementów
jeden komponent