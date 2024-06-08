import React from "react";
import "./FaqPanel.css"; // Importing CSS file for styling
import "bootstrap/dist/css/bootstrap.min.css";

function FaqPanel() {
  const faqData = [
    {
      question: "Co to jest gra miejska?",
      answer:
        "Gra miejska to interaktywna forma zabawy, która polega na wykonywaniu różnorodnych zadań w przestrzeni miejskiej. Uczestnicy poruszają się po mieście, rozwiązując zagadki, szukając wskazówek i wykonując zadania, które są tematycznie związane z historią, kulturą lub innymi aspektami danego miejsca.",
    },
    {
      question: "Jakie są zasady uczestnictwa w grze miejskiej?",
      answer:
        "Zasady mogą się różnić w zależności od konkretnej gry, ale zazwyczaj obejmują: rejestrację uczestników, przestrzeganie zasad fair play, rozwiązywanie zagadek bez pomocy osób postronnych oraz ukończenie gry w określonym czasie. Szczegółowe zasady są zazwyczaj podane przed rozpoczęciem gry.",
    },
    {
      question: "Czy muszę znać miasto, aby wziąć udział w grze miejskiej?",
      answer:
        "Nie jest to konieczne. Gry miejskie są zaprojektowane tak, aby były zrozumiałe i przyjemne zarówno dla mieszkańców, jak i turystów. Wskazówki i mapy pomagają uczestnikom odnaleźć się w mieście.",
    },
    {
      question: "Czy gra miejska jest odpowiednia dla dzieci?",
      answer:
        "Tak, wiele gier miejskich jest dostosowanych do różnych grup wiekowych, w tym dzieci. Organizatorzy często tworzą specjalne wersje gier, które są dostosowane do możliwości i zainteresowań młodszych uczestników.",
    },
    {
      question: "Ile czasu zajmuje ukończenie gry miejskiej?",
      answer:
        "Czas trwania gry miejskiej może się różnić w zależności od jej skomplikowania i długości trasy. Zazwyczaj gry trwają od 1 do 3 godzin, ale niektóre mogą być krótsze lub dłuższe.",
    },
    {
      question:
        "Czy muszę mieć specjalne umiejętności, aby wziąć udział w grze miejskiej?",
      answer:
        "Nie, gry miejskie są zaprojektowane tak, aby były dostępne dla wszystkich. Wymagają głównie umiejętności logicznego myślenia, pracy zespołowej i zdolności do poruszania się po mieście.",
    },
    {
      question:
        "Czy potrzebuję specjalnego sprzętu do udziału w grze miejskiej?",
      answer:
        "Zazwyczaj nie jest potrzebny specjalny sprzęt. Warto jednak zabrać ze sobą wygodne obuwie, telefon z dostępem do internetu oraz wodę.",
    },
    {
      question: "Czy mogę grać samodzielnie, czy muszę mieć zespół?",
      answer:
        "To zależy od konkretnej gry. Niektóre gry miejskie są zaprojektowane dla indywidualnych uczestników, ale większość jest przeznaczona dla zespołów. Gra w grupie może być bardziej zabawna i umożliwia lepszą współpracę przy rozwiązywaniu zadań.",
    },
  ];

  const FAQItem = ({ question, answer }) => (
    <div style={{ marginBottom: "20px" }}>
      <h3>{question}</h3>
      <p>{answer}</p>
    </div>
  );

  const FAQ = () => (
    <div style={{ padding: "20px" }}>
      <h1>FAQ - Gra Miejska</h1>
      {faqData.map((faq, index) => (
        <FAQItem key={index} question={faq.question} answer={faq.answer} />
      ))}
    </div>
  );

  return (
    <div className="faq-container">
      {console.log("Rendering FAQ Panel")}
      <FAQ />
    </div>
  );
}

export default FaqPanel;
