import React, { useCallback, useState } from "react";
import axios from "axios";

function App() {
  const [file, setFile] = useState(null);
  const [files, setFiles] = useState([]);

  const handleChange = (e) => {
    if (e.target.files === null) return;

    if (e.target.files[0]) {
      setFile(e.target.files[0]);
    }
  };

  const handleClick = useCallback(async () => {
    if (!file) return;

    const formData = new FormData();
    formData.append("file", file);

    const res = await axios.post(
      "http://localhost:4000/file/upload",
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      }
    );
    if (res.status === 201) console.log(res.data);
  }, [file]);

  const getVideoList = async () => {
    axios.get("http://localhost:4000/file/file-name").then((res) => {
      console.log(res);
      setFiles(res.data.fileList);
    });
  };

  return (
    <>
      <div>
        <input type={"file"} onChange={handleChange} />
        <button onClick={handleClick}>업로드 요청</button>
      </div>
      <br></br>
      <div>
        <button onClick={getVideoList}>파일 리스트 요청</button>
      </div>
      <div>
        {files.map((file, index) => {
          return (
            <div key={index}>
              <h2>{file.name}</h2>
              {console.log(file.name)}
            </div>
          );
        })}
      </div>
    </>
  );
}

export default App;
