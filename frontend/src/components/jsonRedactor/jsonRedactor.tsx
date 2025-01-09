import { FC, useState } from "react";
import "./jsonRedactor.css";


interface FinalJsonProps {
  signatureMethod: string;
  information: string;
}

interface JsonRedactorProps {
  jsonToSend: string;
  setJsonToSend: React.Dispatch<React.SetStateAction<string>>;
  setFinalJson: React.Dispatch<React.SetStateAction<FinalJsonProps | null>>;
}

export const JsonRedactor: FC<JsonRedactorProps> = ({ jsonToSend, setJsonToSend, setFinalJson }) => {
  const [error, setError] = useState<boolean>(false);
  const [isLoading, setIsLoading] = useState(false);

  const handleChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
    const newValue = event.target.value;
    setJsonToSend(newValue);

    try {
      JSON.parse(newValue);
      setError(false);
    } catch (err) {
      setError(true);
    }
  };

  const handleSendRequest = async () => {
    setIsLoading(true);
    await fetch("http://localhost:8001/api/recommendation", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: jsonToSend,
    }).then((response) => response.json())
      .then((json: FinalJsonProps) => setFinalJson(json))
      .catch((err) => console.log(err))
      .finally(() => setIsLoading(false))
  };

  return (
    <div className="jsonRedactor">
      <div
        className={error ? "jsonRedactor-phText jsonRedactor-err" : "jsonRedactor-phText"}
      >
        <textarea
          className="phText__textarea"
          value={jsonToSend}
          onChange={handleChange}
        />
      </div>
      <button
        className="jsonRedactor__btn"
        onClick={handleSendRequest}
        disabled={error || isLoading}
      >
        {isLoading ? "Отправка..." : "Запросить рекомендацию"}
      </button>
    </div>
  )
}